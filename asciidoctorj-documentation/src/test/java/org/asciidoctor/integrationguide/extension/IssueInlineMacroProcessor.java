package org.asciidoctor.integrationguide.extension;

//tag::include[]

import org.asciidoctor.ast.ContentNode;
import org.asciidoctor.extension.InlineMacroProcessor;
import org.asciidoctor.extension.Name;

import java.util.HashMap;
import java.util.Map;

@Name("issue")                                                           // <1>
public class IssueInlineMacroProcessor extends InlineMacroProcessor {    // <2>

    @Override
    public Object process(                                               // <3>
            ContentNode parent, String target, Map<String, Object> attributes) {

        String href =
                new StringBuilder()
                    .append("https://github.com/")
                    .append(attributes.containsKey("repo") ?
                            attributes.get("repo") : parent.getDocument().getAttr("repo"))
                    .append("/issues/")
                    .append(target).toString();

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("type", ":link");
        options.put("target", href);
        return createPhraseNode(parent, "anchor", target, attributes, options) // <4>
                .convert();                                                // <5>
    }

}
//end::include[]
