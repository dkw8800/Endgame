package sample;

public class Cards {
private String animal;
private String animalpic;
private String info;
private int moves;

public Cards(String animal, String animalpic, String info, int moves)
{
    this.animal = animal;
    this.animalpic = animalpic;
    this.info = info;
    this.moves = moves;
}

public String retanimal()
{
    return animal;
}

public String retanimalpic()
{
    return animalpic;
}

public String retinfo()
{
    return info;
}

public int retmoves()
{
    return moves;
}
}
