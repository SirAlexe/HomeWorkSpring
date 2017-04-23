package ru.mera.samples.domain.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import ru.mera.samples.domain.entities.ImageEntity;

@Repository
public class ImageRepositoryImpl extends AbstractNamedRepository<ImageEntity> implements ImageRepository {

	private static final Log logger = LogFactory.getLog(ImageRepositoryImpl.class);

	@Override
	public void delete(ImageEntity entity) {
		logger.info("====>ImageEntity" + entity.getId() + ", " + entity.getName() + ", " + entity.getImage());
		super.delete(entity);
	}

}
