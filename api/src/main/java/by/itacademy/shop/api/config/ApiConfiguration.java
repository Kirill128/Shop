//package by.itacademy.shop.api.config;
//
//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.core.config.Configurator;
//import org.apache.logging.log4j.core.config.builder.api.*;
//import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ApiConfiguration {
//    private void log4jConfig(){
//        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
//        AppenderComponentBuilder console= builder.newAppender("stdout", "Console");
//        builder.add(console);
//        LayoutComponentBuilder standard  = builder.newLayout("PatternLayout");
//        standard.addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable");
//
//        RootLoggerComponentBuilder rootLogger
//                = builder.newRootLogger(Level.ERROR);
//        rootLogger.add(builder.newAppenderRef("stdout"));
//
//        builder.add(rootLogger);
//        console.add(standard);
//
//        Configurator.initialize(builder.build());
//    }
//
//}
