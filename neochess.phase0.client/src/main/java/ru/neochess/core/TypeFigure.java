package ru.neochess.core;

/**
 * Тип фигур
 * Created by diviz on 11.02.2017.
 */
public enum TypeFigure {
    Agr("A"),           //Агр+
    Horse("D"),         //Конь+
    Werewolf("F"),       //Оборотень +
    Boar(""),           //Кабан--
    Porcupine("B"),      //Дикобраз +
    Rhinoceros("E"),     //Носорог +
    Dragon("C"),        //Дракон +
    Elephant("H"),       //Слон +-
    Leader("G"),         //Вожак +

    Pawn("O"),           //Пешка +-
    Officer("N"),        //Офицер +
    Monk("M"),           //Монах --
    Archer("L"),         //Лучник -
    Desperado(""),      //Сорвиголова --
    Rook("K"),           //Ладья +
    Queen("J"),          //Королева +
    falseKing(""),      //Лже король --
    King("I");           //Король +

    private String figureCode;

    private TypeFigure(String s)
    {
        figureCode = s;
    }

    public String getFigureCode ()
    {
        return figureCode;
    }
}
