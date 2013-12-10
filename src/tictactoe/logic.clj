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

;; move-char - "x" or "o"
;; move-square - squares are numbered from 0-8 as shown above
;; @return - the next move (if the move was played by the player)
(defn compute-next-move [move-char move-square]
	(let [current-state (last board-state)]
		;; create a new state vector with the given move
		(def newest-state (assoc current-state move-square move-char))

		;; append the new state vector to the board-state
		(def board-state (conj board-state newest-state))

		;; return the board state
		;; TODO: make this return the next move (computer's move)
		board-state
	))

;; check the board state and return name of winner
;; false otherwise
(defn is-over? []
	false)
