package com.api.practica.usuario;

import com.api.practica.commons.entity.BaseEntity_;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UsuarioSpecification {
    public static Specification<Usuario> getUsersByEmailSpec(String email) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Usuario_.email), email);
        };
    }

    public static Specification<Usuario> getUsersByAll(String textSearch) {
        return (root, query, criteriaBuilder) -> {
            //id
            Predicate criteriaId = criteriaBuilder.equal(root.get("id"), textSearch.matches("[1-9]+")?Long.valueOf(textSearch):null);

            //email
            Predicate criteriaEmail = criteriaBuilder.like(root.get(Usuario_.email), "%"+textSearch+"%");
            return criteriaBuilder.or(criteriaEmail, criteriaId);
        };
    }
}
