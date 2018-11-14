/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.parsing.antlr.extractor.statement.handler.dialect.sqlserver;

import java.util.Collection;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import com.google.common.base.Optional;

import io.shardingsphere.core.parsing.antlr.extractor.statement.handler.ASTExtractHandler;
import io.shardingsphere.core.parsing.antlr.extractor.statement.handler.RuleName;
import io.shardingsphere.core.parsing.antlr.extractor.statement.handler.result.ExtractResult;
import io.shardingsphere.core.parsing.antlr.extractor.statement.handler.result.PrimaryKeyExtractResult;
import io.shardingsphere.core.parsing.antlr.extractor.statement.util.ASTUtils;

/**
 * Primary key extract handler for SQLServer.
 *
 * @author duhongjun
 */
public final class SQLServerAddPrimaryKeyExtractHandler implements ASTExtractHandler {

    @Override
    public ExtractResult extract(final ParserRuleContext ancestorNode) {
        Optional<ParserRuleContext> addColumnNode = ASTUtils.findFirstChildNode(ancestorNode, RuleName.ADD_COLUMN);
        if (!addColumnNode.isPresent()) {
            return null;
        }
        Optional<ParserRuleContext> tableConstraintNode = ASTUtils.findFirstChildNode(addColumnNode.get(), RuleName.TABLE_CONSTRAINT);
        if (!tableConstraintNode.isPresent()) {
            return null;
        }
        Optional<ParserRuleContext> primaryKeyNode = ASTUtils.findFirstChildNode(tableConstraintNode.get(), RuleName.PRIMARY_KEY);
        if (!primaryKeyNode.isPresent()) {
            return null;
        }
        Collection<ParserRuleContext> result = ASTUtils.getAllDescendantNodes(tableConstraintNode.get(), RuleName.COLUMN_NAME);
        if (result.isEmpty()) {
            return null;
        }
        PrimaryKeyExtractResult extractResult = new PrimaryKeyExtractResult();
        for (ParseTree each : result) {
            extractResult.getPrimaryKeyColumnNames().add(each.getText());
        }
        return extractResult;
    }
}
