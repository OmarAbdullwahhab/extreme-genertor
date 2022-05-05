/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.github.exgen;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.snakeyaml.engine.v2.api.Load;
import org.snakeyaml.engine.v2.api.LoadSettings;

/**
 *
 * @author Omar Abou Arab
 */
public class ExtremeGenerator {

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the ExtremeGenerator!");
        System.out.println("*************************");
        if (args.length < 3) {
            System.out.println("Error: usage ExtremeGenerator <templateDir> <templateName> <yamlModelPath>");
            return;
        }
        Configuration cfg = makeConfiguration(args[0]);
        Template temp = loadTemplate(args[1], cfg);
        mergeModel(temp,args[2]);

    }

    private static Configuration makeConfiguration(String templatesDir) throws IOException {
        // Create your Configuration instance, and specify if up to what FreeMarker
        // version (here 2.3.29) do you want to apply the fixes that are not 100%
        // backward-compatible. See the Configuration JavaDoc for details.
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);

        // Specify the source where the template files come from. Here I set a
        // plain directory for it, but non-file-system sources are possible too:
        cfg.setDirectoryForTemplateLoading(new File(templatesDir));

        // From here we will set the settings recommended for new projects. These
        // aren't the defaults for backward compatibilty.
        // Set the preferred charset template files are stored in. UTF-8 is
        // a good choice in most applications:
        cfg.setDefaultEncoding("UTF-8");

        // Sets how errors will appear.
        // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // Don't log exceptions inside FreeMarker that it will thrown at you anyway:
        cfg.setLogTemplateExceptions(false);

        // Wrap unchecked exceptions thrown during template processing into TemplateException-s:
        cfg.setWrapUncheckedExceptions(true);

        // Do not fall back to higher scopes when reading a null loop variable:
        cfg.setFallbackOnNullLoopVariable(false);
        return cfg;
    }

    private static Template loadTemplate(String path, Configuration cfg) throws MalformedTemplateNameException, ParseException, IOException {
        Template temp = cfg.getTemplate(path);
        return temp;
    }

    private static void mergeModel(Template temp, String yaml) throws TemplateException, IOException {
        LoadSettings settings = LoadSettings.builder().build();
        Load load = new Load(settings);
        Object root =  load.loadFromInputStream(new FileInputStream(yaml));
        Writer out = new OutputStreamWriter(System.out);
        temp.process(root, out);
    }
}
