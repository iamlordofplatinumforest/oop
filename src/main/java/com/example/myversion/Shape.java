package com.example.myversion;

import javafx.scene.paint.Color;

public abstract class Shape {
    // abstract
    // общие хар-ки всех фигур без реализации
    // т.е. цвета и процесс создания

    protected Color inColor;
    protected Color borderColor;
    // protected
    // - инкапсуляция. только этот класс и классы-наследники (расширения)

    public abstract javafx.scene.shape.Shape createShape();

    public void setInColor(Color color) {
        this.inColor = color;
    }
    // Этот метод только изменяет состояние объекта
    // (устанавливает значение поля inColor), поэтому ему не нужно возвращать результат.
    // this - текущий объект

    public void setBorderColor(Color color) {
        this.borderColor = color;
    }
}

