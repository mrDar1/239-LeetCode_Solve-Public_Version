/*problem:
start from 1, return min number of operation before reach "target".
each move can only +1/*2.
number of *2 limited to "maxDoubles", times.*/

/*motivation: start from target to as many division as possible, then decrease till ==1. */

public class B12_MinimumMovestoReachTargetScore_2139 {
    public int minMoves(int target, int maxDoubles) {
        int c = 0; //count number of moves.

        while ( maxDoubles-- > 0 && target > 1){
            if (target % 2 != 0){
                target--;
                c++;
            }
            target /= 2;
            ++c;
        }

        if(target != 1)
        {
            c += target - 1;
        }
//        above line is optimize version of (here slower):
//        while (target-- > 1){
//            ++c;
//        }
        return c;
    }
}