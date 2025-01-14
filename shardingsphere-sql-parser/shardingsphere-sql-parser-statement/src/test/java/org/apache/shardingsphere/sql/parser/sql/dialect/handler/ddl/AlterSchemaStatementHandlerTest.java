/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.sql.parser.sql.dialect.handler.ddl;

import org.apache.shardingsphere.sql.parser.sql.dialect.statement.opengauss.ddl.OpenGaussAlterSchemaStatement;
import org.apache.shardingsphere.sql.parser.sql.dialect.statement.postgresql.ddl.PostgreSQLAlterSchemaStatement;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public final class AlterSchemaStatementHandlerTest {
    
    @Test
    public void assertGetUsernameForPostgreSQL() {
        PostgreSQLAlterSchemaStatement alterSchemaStatement = new PostgreSQLAlterSchemaStatement();
        alterSchemaStatement.setRenameSchema("new_schema");
        Optional<String> actual = AlterSchemaStatementHandler.getRenameSchema(alterSchemaStatement);
        assertTrue(actual.isPresent());
        assertThat(actual.get(), is("new_schema"));
    }
    
    @Test
    public void assertGetUsernameForOpenGauss() {
        OpenGaussAlterSchemaStatement alterSchemaStatement = new OpenGaussAlterSchemaStatement();
        alterSchemaStatement.setRenameSchema("new_schema");
        Optional<String> actual = AlterSchemaStatementHandler.getRenameSchema(alterSchemaStatement);
        assertTrue(actual.isPresent());
        assertThat(actual.get(), is("new_schema"));
    }
}
