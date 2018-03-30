public class GameEngineUseCase {
    GameInfo myInfo;
    List<GameObject> units;
    // This handles the case of a unit attacking another unit
    GameObject a = units.get(ID_1);
    GameObject b = units.get(ID_2);
    Interaction actor = myInfo.getPossibleInteractions(a).get("attack");
    actor.act();
}