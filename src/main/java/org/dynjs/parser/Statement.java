/**
 *  Copyright 2012 Douglas Campos, and individual contributors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.dynjs.parser;

import java.util.List;

import org.dynjs.parser.ast.VariableDeclaration;

import me.qmx.jitescript.CodeBlock;

public interface Statement {

    CodeBlock getCodeBlock();

    Position getPosition();

    int getStatementNumber();
    
    void addLabel(String label);
    List<String> getLabels();

    String dump(String indent);
    
    String toIndentedString(String indent);
    
    List<VariableDeclaration> getVariableDeclarations();

}
