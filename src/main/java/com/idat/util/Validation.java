package com.idat.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.idat.dto.CategoryDto;
import com.idat.exception.ValidationException;

@Component
public class Validation {

	public void categoryValidation(CategoryDto categoryDto) {
		Map<String, Object> error = new LinkedHashMap<>();
		if (ObjectUtils.isEmpty(categoryDto)) {
			throw new IllegalArgumentException("category Object/Json shouldn`tbe null or empty");
		}else {
			//validation name field
			if (ObjectUtils.isEmpty(categoryDto.getName())) {
	            error.put("name", "The name field is required and cannot be null or empty.");
			}else {
				String trimName = categoryDto.getName().trim();
				if (trimName.isEmpty()) {
	                error.put("name", "The name field cannot contain only spaces.");
				}else {
					if (trimName.length()<3) {
	                    error.put("name", "The name field must have at least 3 characters.");
					}if (trimName.length()>100) {
	                    error.put("name", "The name field must not exceed 100 characters.");
					}
				}				
			}
			//validation description
			if (ObjectUtils.isEmpty(categoryDto.getDescription())) {
				error.put("description", "description field is emprty or null");
			}
			//validation isActive
			if (ObjectUtils.isEmpty(categoryDto.getIsActive())) {
				error.put("isActive", "isActive field is emprty or null");
			}else {
				if (categoryDto.getIsActive() != Boolean.TRUE.booleanValue() &&
					categoryDto.getIsActive() != Boolean.FALSE.booleanValue()) {
					error.put("isActive", "invalid value isActive field");
				}
			}		
		}
		if (!error.isEmpty()) {
			throw new ValidationException(error);
		}		
	}
}
