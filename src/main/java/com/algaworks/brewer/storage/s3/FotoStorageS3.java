package com.algaworks.brewer.storage.s3;

import com.algaworks.brewer.storage.*;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.*;
import net.coobird.thumbnailator.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.*;

import java.io.*;

@Profile("prod")
@Component
public class FotoStorageS3 implements FotoStorage {

	private static final Logger logger = LoggerFactory.getLogger(FotoStorageS3.class);

	private static final String BUCKET = "brewertiprep1";

	@Autowired
	private AmazonS3 amazonS3;

	@Override
	public String salvar(MultipartFile[] files) {
		String novoNome = null;
		if (files != null && files.length > 0) {
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename());

			try {
				AccessControlList acl = new AccessControlList();
				acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);

				enviarFoto(novoNome, arquivo, acl);
				enviarThumbnail(novoNome, arquivo, acl);
			} catch (IOException e) {
				throw new RuntimeException("Erro salvando arquivo no S3", e);
			}
		}
		return novoNome;
	}

	@Override
	public byte[] recuperar(String foto) {
		InputStream is = amazonS3.getObject(BUCKET, foto).getObjectContent();
		try {
			return IOUtils.toByteArray(is);
		} catch (IOException e) {
			logger.error("Não conseguiu recuperar foto do S3", e);
		}
		return null;
	}

	@Override
	public byte[] recuperarThumbnail(Object foto) {
		return recuperar(FotoStorage.THUMBNAIL_PREFIX + foto);
	}

	@Override
	public void excluir(String foto) {
		amazonS3.deleteObjects(new DeleteObjectsRequest(BUCKET).withKeys(foto, THUMBNAIL_PREFIX + foto));
	}

	@Override
	public String getUrl(String foto) {
		if (!StringUtils.isEmpty(foto)) {
			return "https://s3-sa-east-1.amazonaws.com/brewertiprep1/" + foto;
					
		}

		return null;
	}

	private ObjectMetadata enviarFoto(String novoNome, MultipartFile arquivo, AccessControlList acl)
			throws IOException {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(arquivo.getContentType());
		metadata.setContentLength(arquivo.getSize());
		amazonS3.putObject(
				new PutObjectRequest(BUCKET, novoNome, arquivo.getInputStream(), metadata).withAccessControlList(acl));
		return metadata;
	}

	private void enviarThumbnail(String novoNome, MultipartFile arquivo, AccessControlList acl) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Thumbnails.of(arquivo.getInputStream()).size(40, 68).toOutputStream(os);
		byte[] array = os.toByteArray();

		InputStream is = new ByteArrayInputStream(array);
		ObjectMetadata thumbMetadata = new ObjectMetadata();
		thumbMetadata.setContentType(arquivo.getContentType());
		thumbMetadata.setContentLength(array.length);
		amazonS3.putObject(new PutObjectRequest(BUCKET, THUMBNAIL_PREFIX + novoNome, is, thumbMetadata)
				.withAccessControlList(acl));
	}

}
