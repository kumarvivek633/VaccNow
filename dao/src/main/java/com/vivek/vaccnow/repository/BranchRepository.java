package com.vivek.vaccnow.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vivek.vaccnow.models.Branch;

/**
 * The Interface BranchRepository.
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Branch> findAll(Pageable pageable);
}
