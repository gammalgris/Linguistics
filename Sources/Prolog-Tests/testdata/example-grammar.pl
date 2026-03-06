is_article(the).
is_article(a).
is_article(an).
is_article(you) :- false.

is_definite_article(the).

is_indefinite_article(a).
is_indefinite_article(an).

is_partitive_article(some).

is_determiner(some).
is_determiner(no).

is_negative_article(no).

is_known(X) :- is_article(X); is_determiner(X).

is_noun(apple).
