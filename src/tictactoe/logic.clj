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
  "Returns true if the move is valid, false and reason if it's not valid"
  [move-char move-square]
  (if (or (> move-square 8) (< move-square 0))
    (throw  (AssertionError. "Invalid move square"))
    (let [current-state (last board-state)

          ;; create a new state vector with the given move
          newest-state  (assoc current-state move-square move-char)

          ;; get the number of x-es in the newest state so we can test 
          x-es-in-new (move-count "x" newest-state)

          ;;if it's out of whack with the number of o-s
          o-s-in-new (move-count "o" newest-state)]
    
    ;;check that a move isn't being played on an already-played square
    (if (some #(= newest-state %) board-state)
      (throw (Exception. "Move has already been played"))
    
      ;;check that the number of x-es is exactly 1 greater than the number of o-s
      ;;because x plays first and compute next move is always called on x (for now)
      (if (> (- x-es-in-new o-s-in-new) 1)
        (throw (Exception. "Board state is fucked up"))
        
        true)))))

;; move-char - "x" or "o"
;; move-square - squares are numbered from 0-8 as shown above
;; @return - the next move (if the move was played by the player)
(defn compute-next-move [move-char move-square]
  {:pre [(is-valid-move? move-char move-square)] }
    (let [newest-state (assoc (last board-state) move-square move-char)]
      ;; append the new state vector to the board-state
      (def board-state (conj board-state newest-state))

      ;; return the board state
      ;; TODO: make this return the next move (computer's move)
      board-state))

;; check the board state and return name of winner
;; false otherwise
(defn is-over? [] false)
