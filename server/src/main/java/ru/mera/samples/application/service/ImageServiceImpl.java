/*
 * Copyright 2002-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.mera.samples.application.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ru.mera.samples.application.dto.ImageDTO;
import ru.mera.samples.domain.dao.ImageRepository;
import ru.mera.samples.domain.entities.ImageEntity;

public class ImageServiceImpl extends AbstractServiceImpl<ImageDTO, ImageEntity> implements ImageService {

	private static final Log logger = LogFactory.getLog(ImageServiceImpl.class);

	@Autowired
	private ImageRepository imageRepository;

	@Override
	public ImageRepository getRepository() {
		return imageRepository;
	}
	
}
