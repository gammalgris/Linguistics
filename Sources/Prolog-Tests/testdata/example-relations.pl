
is_male(John).
is_male(Mary) :- false.

is_female(John) :- false.
is_female(Mary).

has_children(John).
has_children(Mary) :- false.

is_parent(X) :- has_children(X).

is_father(X) :- has_children(X), is_male(X).

is_mother(X) :- has_children(X), is_female(X).
