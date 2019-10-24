package com.tstu;

import java.util.Random;

public class Game {


    private int bulls;
    private int cows;

    public int getBulls() {
        return bulls;
    }

    public int getCows() {
        return cows;
    }

    /**
     * Проверка на корректность размера последовательности
     *
     * @param size Размер
     * @return Подходит размер или нет
     */
    public boolean isIncorrectSize(int size) {
        return size <= 2 || size >= 6;
    }

    /**
     * Возвращает статус игры
     * GIVE_UP - Игрок сдался
     * MISTAKE - Ошибка при вводе данных
     * WIN - Игрок отгадал последовательность/Победа
     * CONTINUE - Игрок не отгадал последовательность/Игра продолжается
     *
     * @param playerResponse Данные введенные игроком
     * @param generatedNumber Загаданная последовательность цифр
     * @return статус игры
     */
    public Status getResultStatus(String playerResponse, String generatedNumber) {
        if (playerResponse.equals("сдаюсь")) {
            return Status.GIVE_UP;
        }
        return playerNumberIsCorrect(playerResponse, generatedNumber)
                ? checkingNumbers(playerResponse, generatedNumber)
                : Status.MISTAKE;
    }

    /**
     * Создание последовательности цифр
     *
     * @param size Размер последовательности цифр
     * @return Последовательность уникальных цифр
     */
    public String randomNumber(int size) {

        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; ) {
            array[i] = random.nextInt(10);
            boolean isUnique = true;
            for (int j = 0; j < i && isUnique; j++) {
                if (array[i] == array[j]) {
                    isUnique = false;
                }
            }
            if (isUnique) {
                i++;
            }
        }
        StringBuilder number = new StringBuilder();
        for (int digit : array) {
            number.append(digit);
        }
        return number.toString();
    }

    /**
     * Посчёт количества быков и коров
     *
     * @param playerResponse Введённая пользователем последовательность цифр
     * @param generatedNumber Загаданная последовательность цифр
     * @return статус игры
     */
    private Status checkingNumbers(String playerResponse, String generatedNumber) {
        bulls = 0;
        cows = 0;
        for (int i = 0; i < playerResponse.length(); i++) {
            for (int j = 0; j < generatedNumber.length(); j++) {
                if (playerResponse.charAt(i) == generatedNumber.charAt(j)) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                    break;
                }
            }
        }
        if (bulls == generatedNumber.length()) {
            return Status.WIN;
        }
        return Status.CONTINUE;
    }

    /**
     * Проверка на то что символ это цифра
     *
     * @param symbol Символ введённой пользователем последовательности
     * @return Является ли символ цифрой
     */
    private boolean symbolIsDigit(char symbol) {
        return symbol <= '9' && symbol >= '0';
    }

    /**
     *Проверка на корректность данных пользователя
     *
     * @param playerNumber Введённая пользователем последовательность цифр
     * @param generatedNumber Загаданная последовательность цифр
     * @return Корректно ли число
     */
    private boolean playerNumberIsCorrect(String playerNumber, String generatedNumber) {
        if (playerNumber.length() != generatedNumber.length()) {
            return false;
        }

        for (int i = 0; i < playerNumber.length(); i++) {
            if (!symbolIsDigit(playerNumber.charAt(i))) {
                return false;
            }
            for (int j = 0; j < i; j++) {
                if (playerNumber.charAt(i) == playerNumber.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
