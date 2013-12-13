(ns tictactoe.logic)

;;   0  |  1  |  2
;; _____|_____|_____
;    3  |  4  |  5
;  _____|_____|_____
;    6  |  7  |  8
;       |     |

;; board state is saved as a list of vectors. each item represents one turn played
;; ["" "x" "" "" "" "" "" "" ""] represents a board with an "x" on square 1
(def board-state [(vec (repeat 9 ""))])

;;count the number occurrences of move in a given-state
(defn move-count [move given-state]
  (count (filter #(= move %) given-state)))

(defn is-valid-move? 
  "Returns true if the move is valid, throws an exception if not"
  [move-char move-square board]
  (if (or (> move-square 8) (< move-square 0))
    (throw  (AssertionError. "Invalid move square"))
    (let [current-state (last board)

          ;; create a new state vector with the given move
          newest-state  (assoc current-state move-square move-char)

          ;; get the number of x-es in the newest state so we can test 
          x-es-in-new (move-count "x" newest-state)

          ;;if it's out of whack with the number of o-s
          o-s-in-new (move-count "o" newest-state)]
    
    ;;check that a move isn't being played on an already-played square
    (if (some #(= newest-state %) board)
      (throw (Exception. "Move has already been played"))
    
      ;;check that the number of x-es is exactly 1 greater than the number of o-s
      ;;because x plays first and compute next move is always called on x (for now)
      (if (> (- x-es-in-new o-s-in-new) 1)
        (throw (Exception. "Board state is fucked up"))

        true)))))

;; move-char - "x" or "o"
;; move-square - squares are numbered from 0-8 as shown above
;; @return - the next move (if the move was played by the player)
(defn compute-next-move [move-char move-square board]

  {:pre [(is-valid-move? move-char move-square board)] }

    (let [newest-move (assoc (last board) move-square move-char)

          ;;find the blank squares on the board
          blank-squares (keep-indexed (fn [idx value] (if (= value "") idx nil )) newest-move)]

      (print blank-squares)

      ;; want to make a game engine that will always at least draw
      ;; so it will look to play the 4 square 1st, then 0, 2, 6, 8 (the corners)
      ;; with equal priority, then 1, 3, 5, 7 with also equal priority

      ;; 1. check if 4 is played
      (if (some #{4} blank-squares)
        (do
          (print "four isn't played")
          (assoc newest-move 4 "o"))

        ;; 2. if it has been played (by either player), find the first of 0, 2, 6, 8 that
        ;;    haven't been played, and do it
        (let [free-corner (some #{0 2 6 8} blank-squares)]
          (if free-corner
            (do 
              (print free-corner " is a free corner")
              (assoc newest-move free-corner "o"))
          ;; 3. if no corner square is free, find the first of 1, 3, 5, 7 that hasn't been played
            (let [free-side (some #{1 3 5 7} blank-squares)]
              (if free-side
                (do 
                  (print free-side " is a free side")
                  (assoc newest-move free-side "o"))
                (print "none of them worked"))))))))

;; check the board state and return name of winner
;; false otherwise
(defn is-over? [] false)
