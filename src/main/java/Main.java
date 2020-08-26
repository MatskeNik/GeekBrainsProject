public class Main {

    public static void main(String[] args) {
        Barrier[] barriers = {
                new Wall(1),
                new RunningTrack(2),
                new Wall(3),
                new RunningTrack(5),
                new Wall(5),
                new RunningTrack(10)
        };

        Participant[] runners = {
                new Human(3,2),
                new Cat(2,5),
                new Robot(11,11)
        };

        for(int i = 0; i < runners.length; i++) {
            for(int y = 0; y < barriers.length; y++) {
                if(barriers[y].getHeight() > 0) {
                    runners[i].jump();
                    if(runners[i].getMaxHeight() > barriers[y].getHeight()) {
                        System.out.println("И успешно перепрыгивает препятствие");
                    } else {
                        System.out.println("но не смог перепрыгнуть препятствие");
                        break;
                    }
                } else {
                    runners[i].run();
                    if(runners[i].getMaxDistance() > barriers[y].getLength()) {
                        System.out.println("И успешно пробегает препятствие");
                    } else {
                        System.out.println("но не смог пробежать препятствие");
                        break;
                    }
                }
            }
            System.out.println("=============================================================");
        }
    }
}
