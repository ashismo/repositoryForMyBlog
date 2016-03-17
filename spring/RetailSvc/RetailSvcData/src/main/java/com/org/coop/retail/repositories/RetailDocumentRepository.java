package com.org.coop.retail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.retail.entities.RetailDocument;

public interface RetailDocumentRepository extends JpaRepository<RetailDocument, Integer> {
}
