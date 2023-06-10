(ns difference-of-squares)

(defn square
  [x]
  (* x x))

(defn sum-of-squares
  [N]
  (->> N
       inc
       (range 1)
       (map square)
       (reduce +)))

(defn square-of-sum
  [N]
  (->> N
       inc
       (range 1)
       (reduce +)
       square))

(defn difference
  [N]
  (- (square-of-sum N) (sum-of-squares N)))
