package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>{

	@Query("select i.reports from Immigrant i where i.id = ?1")
	Collection<Report> findReportByImmigrantId(int immigrantId);
}