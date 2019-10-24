package com.tstu;

import java.util.Scanner;

public class GameInteraction {

    private static int numberOfAttempts;
    private Game newGame;
    /**
     * Вводим размер последовательности цифр
     * Проверка введённых данных пользователя
     */
    public void startGame() {

        System.out.print("Введите длину загадываемой последовательности(от 3 до 5): ");
        int size = 0;
        newGame=new Game();

        do {
            try {
                Scanner in = new Scanner(System.in);
                size = in.nextInt();
                if (newGame.isIncorrectSize(size)) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.print("Невернные данные. Введите заново: ");
            }
        } while (newGame.isIncorrectSize(size));

        numberOfAttempts=1;

        String generatedNumber = newGame.randomNumber(size);
        executeGame(generatedNumber);
    }


    /**
     * Исполнение игры
     * Попытка угадать пользователем последовательность цифр
     *
     * @param generatedNumber Загаданная последовательность цифр
     */
    private void executeGame(String generatedNumber) {
        Scanner in = new Scanner(System.in);
        Status status;
        do {
            System.out.print("Попытка №" + numberOfAttempts + ". Угадайте последовательность: ");
            String playerResponse = in.next();
            status = newGame.getResultStatus(playerResponse, generatedNumber);
            statusHandler(status, generatedNumber);
        }while (status!=Status.GIVE_UP && status!=Status.WIN);
        resetGame();
    }


    /**
     * Обработка статуса игры
     *
     * @param status Статус игры
     * @param generatedNumber Загаданная последовательность цифр
     */
    private void statusHandler(Status status, String generatedNumber) {
        switch (status) {
            case MISTAKE:
                System.out.println(Status.MISTAKE.getValue());
                break;
            case GIVE_UP:
                System.out.println(Status.GIVE_UP.getValue());
                System.out.println("Загаданная последовательность:" + generatedNumber);
                break;
            case CONTINUE:
                numberOfAttempts++;
                System.out.println("Кол-во коров: " + newGame.getCows());//-!
                System.out.println("Кол-во быков: " + newGame.getBulls());//-!
                break;
            case WIN:
                System.out.println(Status.WIN.getValue());
                System.out.println("Количество попыток:" + numberOfAttempts);
                break;
        }
    }


    /**
     * Перезапуск игры
     */
    private void resetGame() {
        Scanner in = new Scanner(System.in);
        System.out.print("Перезапустить игру(да/нет):");
        String response = in.next();

        if (response.equals("да")) {
            startGame();
        }
        if (!response.equals("нет")) {
            resetGame();
        }
    }
}
