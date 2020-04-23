(defmodule MAIN (export ?ALL))

;; ------------------- INITIAL STATE --------------------

(deftemplate MAIN::attribute
   (slot name)
   (slot value)
   (slot certainty (default 100.0)))

(defrule MAIN::start
  (declare (salience 10000))
  =>
  (set-fact-duplication TRUE)
  (focus SUGGEST-TYPE PIZZA))

(defrule MAIN::combine-certainties ""
  (declare (salience 100)
           (auto-focus TRUE))
  ?rem1 <- (attribute (name ?rel) (value ?val) (certainty ?per1))
  ?rem2 <- (attribute (name ?rel) (value ?val) (certainty ?per2))
  (test (neq ?rem1 ?rem2))
  =>
  (retract ?rem1)
  (modify ?rem2 (certainty (/ (- (* 100 (+ ?per1 ?per2)) (* ?per1 ?per2)) 100))))
  
 
;; ------------------- The RULES module  ------------------- 

(defmodule RULES (import MAIN ?ALL) (export ?ALL))

(deftemplate RULES::rule
  (slot certainty (default 100.0))
  (multislot if)
  (multislot then))

(defrule RULES::throw-away-ands-in-antecedent
  ?f <- (rule (if and $?rest))
  =>
  (modify ?f (if ?rest)))

(defrule RULES::throw-away-ands-in-consequent
  ?f <- (rule (then and $?rest))
  =>
  (modify ?f (then ?rest)))

(defrule RULES::remove-is-condition-when-satisfied
  ?f <- (rule (certainty ?c1) 
              (if ?attribute is ?value $?rest))
  (attribute (name ?attribute) 
             (value ?value) 
             (certainty ?c2))
  =>
  (modify ?f (certainty (min ?c1 ?c2)) (if ?rest)))

(defrule RULES::remove-is-not-condition-when-satisfied
  ?f <- (rule (certainty ?c1) 
              (if ?attribute is-not ?value $?rest))
  (attribute (name ?attribute) (value ~?value) (certainty ?c2))
  =>
  (modify ?f (certainty (min ?c1 ?c2)) (if ?rest)))

(defrule RULES::perform-rule-consequent-with-certainty
  ?f <- (rule (certainty ?c1) 
              (if) 
              (then ?attribute is ?value with certainty ?c2 $?rest))
  =>
  (modify ?f (then ?rest))
  (assert (attribute (name ?attribute) 
                     (value ?value)
                     (certainty (/ (* ?c1 ?c2) 100)))))

(defrule RULES::perform-rule-consequent-without-certainty
  ?f <- (rule (certainty ?c1)
              (if)
              (then ?attribute is ?value $?rest))
  (test (or (eq (length$ ?rest) 0)
            (neq (nth 1 ?rest) with)))
  =>
  (modify ?f (then ?rest))
  (assert (attribute (name ?attribute) (value ?value) (certainty ?c1))))

 ;-------------------------------------------------------------------------

(defmodule SUGGEST-TYPE (import RULES ?ALL)
                            (import MAIN ?ALL))

(defrule SUGGEST-TYPE::startit => (focus RULES))

;-------------------------------------------------------------------------
; RULES
;-------------------------------------------------------------------------

(deffacts the-pizza-rules

	(rule (if has-sauce is no) (then best-sauce is tomato with certainty 45))
	(rule (if has-sauce is unknown) (then best-sauce is spicy with certainty 85 and best-sauce is tomato with certainty 95))
	(rule (if has-sauce is yes and sauce is spicy) (then best-sauce is spicy))
	(rule (if has-sauce is yes and sauce is garlic) (then best-sauce is garlic with certainty 85))
	(rule (if has-sauce is yes and sauce is tomato) (then best-sauce is tomato with certainty 95))
	(rule (if has-sauce is yes) (then best-sauce is tomato with certainty 82))
	
	(rule (if has-nonvegi is no) (then best-main-ingredient is vegi with certainty 100))
	(rule (if has-nonvegi is yes and main-ingredient is chicken) (then best-main-ingredient is chicken))
	(rule (if has-nonvegi is yes and main-ingredient is mutton) (then best-main-ingredient is mutton with certainty 98 and best-main-ingredient is chicken with certainty 35))
	(rule (if has-nonvegi is yes and main-ingredient is fish) (then best-main-ingredient is fish with certainty 78 and best-main-ingredient is prawns with certainty 65))
	(rule (if has-nonvegi is yes and main-ingredient is prawns) (then best-main-ingredient is prawns with certainty 95 and best-main-ingredient is fish with certainty 55))
	(rule (if has-nonvegi is yes and main-ingredient is beef) (then best-main-ingredient is beef))
	
	(rule (if has-cheese is no) (then best-cheese is mozzarella with certainty 12))
	(rule (if has-cheese is yes and cheese is mozzarella) (then best-cheese is mozzarella with certainty 85 and best-cheese is double with certainty 98))
	(rule (if has-cheese is yes and cheese is extra) (then best-cheese is extra with certainty 100 and best-cheese is double with certainty 35))	
	
	(rule (if ingredient is onion) (then best-ingredient is onion))
	(rule (if ingredient is chilli) (then best-ingredient is chilli))
	(rule (if ingredient is olive) (then best-ingredient is olive))
	(rule (if ingredient is pepper) (then best-ingredient is pepper))
	(rule (if ingredient is tomato) (then best-ingredient is tomato))
	(rule (if ingredient is pineapple) (then best-ingredient is pineapple))
	(rule (if ingredient is carrot) (then best-ingredient is carrot))
	(rule (if ingredient is mushroom) (then best-ingredient is mushroom))
)
		
;-------------------------------------------------------------------------
; PIZZA DEFINITIONS
;-------------------------------------------------------------------------

(defmodule PIZZA (import MAIN ?ALL)
                 (export deffunction get-pizza-list))

(deffacts any-attributes
  (attribute (name best-main-ingredient) (value any))
  (attribute (name best-sauce) (value any))
  (attribute (name best-cheese) (value any))
  (attribute (name best-ingredient) (value any)))

(deftemplate PIZZA::pizza
  (slot name (default ?NONE))
  (multislot vegetarian (default no))
  (multislot main-ingredient (default any))
  (multislot ingredient (default any))
  (multislot sauce  (default tomato))
  (multislot cheese (default any)))

(deffacts PIZZA::the-pizza-list 
  (pizza (name "Devilled Chicken") (vegetarian no) (main-ingredient chicken) (sauce spicy) (cheese mozzarella double))
  (pizza (name "Corned Mutton Sensation") (vegetarian no) (main-ingredient mutton) (ingredient onion chilli) (cheese mozzarella double))
  (pizza (name "Moghul Chicken Tandoori") (vegetarian no) (main-ingredient chicken) (ingredient mango gouda) (cheese mozzarella))
  (pizza (name "Hot & Spicy Chicken") (vegetarian no) (main-ingredient chicken)  (ingredient capsicum onion) (cheese mozzarella double))
  (pizza (name "Sausage Delight") (vegetarian no) (main-ingredient chicken) (ingredient onion) (cheese mozzarella double))
  (pizza (name "Tandoori Chicken") (vegetarian no) (main-ingredient chicken) (ingredient onion) (cheese mozzarella double))
  (pizza (name "Devilled Fish") (vegetarian no) (main-ingredient fish)(ingredient onion capsicum chilli)  (cheese mozzarella double))
  (pizza (name "Spicy Seafood") (vegetarian no) (main-ingredient prawns) (ingredient olive bellpepper onion) (cheese mozzarella double))
  (pizza (name "Chillie Chicken") (vegetarian no) (main-ingredient chicken) (ingredient chilli) (sauce spicy)(cheese mozzarella double))
  (pizza (name "BBQ Chicken") (vegetarian no) (main-ingredient chicken) (ingredient onion pineapple pepper) (cheese mozzarella double))
  (pizza (name "Hot Garlic Prawns") (vegetarian no) (main-ingredient prawns) (ingredient onion pepper tomato) (sauce garlic) (cheese mozzarella))
  (pizza (name "Cheese Lovers") (vegetarian yes) (main-ingredient vegi) (sauce tomato) (cheese mozzarella extra))
  (pizza (name "Chicken Hawaiian") (vegetarian no) (main-ingredient chicken) (ingredient pineapple) (cheese mozzarella double))
  (pizza (name "Meat Lovers") (vegetarian no) (main-ingredient chicken) (cheese mozzarella double))
  (pizza (name "Veggie Supreme") (vegetarian yes) (main-ingredient vegi) (ingredient mushroom tomato onion olive bellpepper) (cheese mozzarella double))
  (pizza (name "Cheesy Onion") (vegetarian yes) (main-ingredient vegi) (ingredient onion) (cheese mozzarella double))
  (pizza (name "Beef Pepperoni") (vegetarian no) (main-ingredient beef) (sauce tomato) (cheese mozzarella double))
  (pizza (name "Ultimate Cheese Treat") (vegetarian yes) (main-ingredient vegi) (ingredient cheddar gouda edam parmesan) (cheese mozzarella double extra))
  (pizza (name "Spicy Veggie with Paneer") (vegetarian yes) (main-ingredient vegi) (ingredient capsicum leeks carrot cabbage pineapple paneer) (cheese mozzarella double))
  (pizza (name "Super Supreme") (vegetarian no) (main-ingredient chicken, beef) (ingredient pineapple pepper mushroom olive) (cheese mozzarella double))
  (pizza (name "Chicken Bacon") (vegetarian no) (main-ingredient chicken) (ingredient onion chilli) (cheese mozzarella double))
  (pizza (name "Cheese & Tomato") (vegetarian yes) (main-ingredient vegi) (ingredient tomato) (cheese mozzarella double))
  (pizza (name "Chicken Bacon & Potato") (vegetarian no) (main-ingredient chicken) (ingredient potato chilli onion) (cheese mozzarella double))
  (pizza (name "Veggie Stravaganza") (vegetarian yes) (main-ingredient vegi) (ingredient mushroom tomato onion olive corn) (cheese mozzarella)))
		 
(defrule PIZZA::generate-pizza
  (pizza (name ?name)
        (main-ingredient $? ?c $?)
        (sauce $? ?b $?)
        (cheese $? ?s $?))
  (attribute (name best-main-ingredient) (value ?c) (certainty ?certainty-1))
  (attribute (name best-sauce) (value ?b) (certainty ?certainty-2))
  (attribute (name best-cheese) (value ?s) (certainty ?certainty-3))
  =>
  (assert (attribute (name pizza) (value ?name)
                     (certainty (min ?certainty-1 ?certainty-2 ?certainty-3)))))

(deffunction PIZZA::pizza-sort (?w1 ?w2)
   (< (fact-slot-value ?w1 certainty)
      (fact-slot-value ?w2 certainty)))
      
(deffunction PIZZA::get-pizza-list ()
  (bind ?facts (find-all-facts ((?f attribute))
                               (and (eq ?f:name pizza)
                                    (>= ?f:certainty 20))))
  (sort pizza-sort ?facts))
  

