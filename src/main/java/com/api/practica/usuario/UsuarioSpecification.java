package com.api.practica.usuario;

import com.api.practica.commons.entity.BaseEntity_;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class UsuarioSpecification {
    public static Specification<Usuario> getUsersByEmailSpec(String email) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Usuario_.email), email);
        };
    }

    public static Specification<Usuario> getUsersByAll(String textSearch) {
        return (root, query, criteriaBuilder) -> {
            //id
            Predicate criteriaId = criteriaBuilder.equal(root.get(Usuario_.id),textSearch.matches("[0-9]+")? Long.valueOf(textSearch) :null);

            //email
            Predicate criteriaEmail = criteriaBuilder.like(root.get(Usuario_.email), "%"+textSearch+"%");
            return criteriaBuilder.or(criteriaEmail, criteriaId);
        };
    }
}
