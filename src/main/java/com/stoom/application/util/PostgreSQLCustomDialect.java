package com.stoom.application.util;

import org.hibernate.dialect.PostgreSQL95Dialect;
import org.hibernate.tool.schema.extract.internal.SequenceInformationExtractorNoOpImpl;
import org.hibernate.tool.schema.extract.spi.SequenceInformationExtractor;

/**
 * Class that represents a Custom Dialect for PostgreSQL.
 */
public class PostgreSQLCustomDialect extends PostgreSQL95Dialect {

	/**
	 * Responsible for query sequence for table {@code Address} previously created in database.
	 */
	public String getQuerySequencesString() {
		return "select * from address_id_seq";
	}

	/**
	 * Get the information about sequences.
	 */
	public SequenceInformationExtractor getSequenceInformationExtractor() {
		return SequenceInformationExtractorNoOpImpl.INSTANCE;
	}
}