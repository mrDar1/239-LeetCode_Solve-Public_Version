/*problem:
* return max match-number of players and trainers.
* to make match:
* 1-each player got to be equally or less skill than trainer.
* 2-each trainer can have at most 1 player. */

/*motivation: prioritize first lowest trainer and players - when match remove both, if not match check next trainer.*/

import java.util.PriorityQueue;
public class B5_MaximumMatchingofPlayersWithTrainers_2410 {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        PriorityQueue <Integer> p_pq = new PriorityQueue<>();//players
        PriorityQueue <Integer> t_pq = new PriorityQueue<>();//trainers
        int ans = 0;

        for (int i : players){
            p_pq.offer(i);
        }
        for (int i : trainers){
            t_pq.offer(i);
        }

        while ( !p_pq.isEmpty() && !t_pq.isEmpty()){
            int cur = t_pq.poll();

            if (cur >= p_pq.peek()){
                ++ans;
                p_pq.poll();
            }
        }

        return ans;
    }
}