(ns diamond)

(def letters "ABCDEFGHIJKLMNOPQRSTUVWXYZ")

(defn get-index
  [ch]
  (- (int ch) (int \A)))

(defn get-center-row-size
  [ch]
  (inc (* 2 (get-index ch))))

(defn repeat-space
  [n]
  (apply str (take n (repeat \space))))

(defn create-row
  [ch idx]
  (let [center (repeat-space (- (get-center-row-size ch) 2))
        side (repeat-space idx)]
    (if (= center "")
      (str side ch side)
      (str side ch center ch side))))

(defn diamond
  [ch]
  (let [index (get-index ch)
        top-half (mapv create-row (subs letters 0 index) (range index 0 -1))
        center (create-row ch 0)
        bottom-half (rseq top-half)]
    (into (conj top-half center) bottom-half)))
