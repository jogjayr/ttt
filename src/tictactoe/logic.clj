(ns tictactoe.logic)
(def board-state [(vec (repeat 9 ""))])

(defn compute-next-move [move-char move-square]
	(let [current-state (last board-state)]
		(def newest-state (assoc current-state move-square move-char))
		(def board-state (conj board-state newest-state))
		board-state
	))

; (defn is-over? []
; 	(if (= board-state)))
