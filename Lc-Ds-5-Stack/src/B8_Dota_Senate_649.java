import java.util.LinkedList;
import java.util.Queue;
/*problem:
* return - name of winner party.
* each round can ban one of the other team. when remain only one team - they are the winner!
* can ban any competitor, but the order of the game is from left to right!
* constraint - each time doing the best choice they can! */

/*intuition - what is the best strategy:
* for each turn - greedly ban the closest opponent
* so we take pairs of 1 from each group and ban the second.*/

/*solutions:
* 1st - 2 Q's - much more readable.
* 2nd - same idea as 1, but 1 Q and counters - same time and space but other implementation */

public class B8_Dota_Senate_649 {
    public static void main(String[] args){
        String s1 = "RD";
        String s2 = "RDD";
        String s3 = "DRDRD";
        Solution_Dota_Senate_649 obj_649 = new Solution_Dota_Senate_649();

        System.out.println(obj_649.predictPartyVictory_2q(s1));
        System.out.println(obj_649.predictPartyVictory_2q(s2));
        System.out.println(obj_649.predictPartyVictory_2q(s3));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_Dota_Senate_649 {
public String predictPartyVictory_2q(String senate) {
//    1st 2q:
        /*my implement psudo:
         * create 2 Q: 1-d, 1-r
         * while both Q not empty - each time pop from both, and then "offer" back only to the
         *  first of them (second left, as first "banned" him).
         * whenever one of Q goes empty the other winner */

        int len = senate.length();

        // Queues with Senator's Index.
        // Index will be used to find the next turn of Senator
        Queue<Integer> rQueue = new LinkedList<>(); //hold index in senate string.
        Queue<Integer> dQueue = new LinkedList<>(); //hold index in senate string.

//        populate Queues:
        for (int i = 0; i < len; i++) {
            if (senate.charAt(i) == 'D'){
                dQueue.offer(i);
            } else {
                rQueue.offer(i);
            }
        }

        while ( !rQueue.isEmpty() && !dQueue.isEmpty()){
            int d = dQueue.poll();
            int r = rQueue.poll();

            if (d < r){ //hold indexes - means d come before r
                dQueue.offer(d + len); // +len - to save index for next round.
            } else {
                rQueue.offer(r + len);
            }
        }
    //        time: O2n - 1st populated Qs, 2nd - choose which to ban.
    //        space: O1n - size of senate.
        return dQueue.isEmpty() ? "Radiant" : "Dire";
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//LC give several solutions - here i chose the 5th one - with a single Q.
    public String predictPartyVictory_1q(String senate) {
        // Number of Senators of each party
        int rCount = 0;
        int dCount = 0;

        // Floating Ban Count
        int dFloatingBan = 0;
        int rFloatingBan = 0;

        // Queue of Senators - for both parties
        Queue<Character> q = new LinkedList<>();

        // populate q & count number of senator
        for (char c : senate.toCharArray()) {
            q.add(c);

            if (c == 'R'){
                rCount++;
            } else {
                dCount++;
            }
        }

        // While any party has eligible Senators
        while (rCount > 0 && dCount > 0) {

            // Pop the senator with turn
            char curr = q.poll();

            // If eligible, float the ban on the other party, enqueue again.
            // If not, decrement the floating ban and count of the party.
            if (curr == 'D') {
                if (dFloatingBan > 0) {
                    dFloatingBan--;
                    dCount--;
                } else {
                    rFloatingBan++;
                    q.add('D');
                }
            } else {
                if (rFloatingBan > 0) {
                    rFloatingBan--;
                    rCount--;
                } else {
                    dFloatingBan++;
                    q.add('R');
                }
            }
        }

        return rCount > 0 ? "Radiant" : "Dire";
    }
}
