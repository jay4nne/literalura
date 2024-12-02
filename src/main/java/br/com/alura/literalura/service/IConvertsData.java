package br.com.alura.literalura.service;

public interface IConvertsData {
    <T> T getData(String json, Class<T> classe);
}

