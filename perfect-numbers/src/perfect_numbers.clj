(ns perfect-numbers)

(defn- composites
  "Returns a lazy seq of composites in [2, max].
  Even numbers are left out."
  [max]
  (for [i (range 3 (inc (Math/round (Math/sqrt max))) 2)
        k (range i (inc (/ max i)) 2)]
    (* i k)))

(defn sieve
  "Returns all primes in [2, max]. "
  [max]
  (if (< max 2)
    ()
    (vec (remove (set (composites max))
                 (cons 2 (range 3 (inc max) 2))))))

(defn- prime?
  "Returns false if n has a factor in the given list
  of primes or if n = 1. Returns true otherwise."
  [n primes]
  (if (= n 1)
    false
    (every? #(not= 0 (mod n %))
            primes)))

(defn of
  "Returns the prime factors of n. Some of them may be
  repeated more than 1 time."
  ([n]
   (if (= n 1)
     []
     (let [primes (sieve (Math/round (Math/sqrt n)))]
       (of n primes))))
  ([n primes]
   (loop [result []
          num n
          i 0]
     (let [nth-prime (get primes i)]
       (if nth-prime
         (let [q (/ num nth-prime)]
           (if (int? q)
             (if (prime? q primes)
               (recur (conj result nth-prime q) q i)
               (recur (conj result nth-prime) q i))
             (recur result num (inc i))))
         (if (seq result)
           result
           [n]))))))

(defn classify
  "Determines if a number is perfect, abundant, or deficient."
  [n]
  (if (pos? n)
    (let [factors (of n)]
      (loop [result 1
             num n
             [factor & rest-factors] factors]
        (if (seq rest-factors)
          (let [fact1 (/ num factor)
                fact2 (/ n fact1)]
            (recur (+ result fact1 fact2) fact1 rest-factors))
          (cond
            (> result n) :abundant
            (= result n) :perfect
            (< result n) :deficient))))
    (throw (IllegalArgumentException.))))
