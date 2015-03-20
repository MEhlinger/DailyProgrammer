#!/usr/bin/perl

# Fizzbuzz in PERL
# by Marshall Ehlinger

use strict;
use warnings;

my $MAX = 100;

for (my $i = 0; $i <= $MAX; $i++) {
	my $line = "";
	if ($i % 3 == 0) { $line .= "fizz"; }
	if ($i % 5 == 0) { $line .= "buzz"; }
	if ($line eq "") { $line = $i; }
	print("$line\n");
}