(ns tictactoe.asciiboard)

(defn fill-board [board]
  (let [ascii-board 
         "            0  |  1  |  2
          _____|_____|_____
            3  |  4  |  5     
          _____|_____|_____
            6  |  7  |  8  
               |     |     \n"]
    (if (= [] board)
	   ascii-board
     (loop [idx 0
            return-board ascii-board]
          (if (< idx (count board))
            (let [move (board idx)]
              (if (not= "" move)
                (recur (inc idx) (.replace return-board (str idx) move))
                (recur (inc idx) return-board)))
              return-board)))))

(defn draw-board [board]
  (print (fill-board board)))
