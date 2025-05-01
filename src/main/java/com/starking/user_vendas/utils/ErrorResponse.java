package com.starking.user_vendas.utils;

/**
 * @author pedroRhamon
 */
public record ErrorResponse<T>(T data, String message) {}

