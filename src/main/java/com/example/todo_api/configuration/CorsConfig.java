//package com.example.todo_api.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer{
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // Разрешаем CORS для всех URL
//                .allowedOriginPatterns("*") // Используем allowedOriginPatterns вместо allowedOrigins для гибкости
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Все стандартные методы
//                .allowedHeaders("*") // Разрешаем любые заголовки
//                .allowCredentials(false); // Без куки и авторизации — безопаснее и проще
//    }
//}