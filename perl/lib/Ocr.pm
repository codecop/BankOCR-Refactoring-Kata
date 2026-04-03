package Ocr;
use Moose;

my @NUMERALS = (
    [ ' _  ', '| | ', '|_| ', '    ' ],
    [ '    ', '  | ', '  | ', '    ' ],
    [ ' _  ', ' _| ', '|_  ', '    ' ],
    [ ' _  ', ' _| ', ' _| ', '    ' ],
    [ '    ', '|_| ', '  | ', '    ' ],
    [ ' _  ', '|_  ', ' _| ', '    ' ],
    [ ' _  ', '|_  ', '|_| ', '    ' ],
    [ ' _  ', '  | ', '  | ', '    ' ],
    [ ' _  ', '|_| ', '|_| ', '    ' ],
    [ ' _  ', '|_| ', ' _| ', '    ' ],
);

sub parse {
    my ( $self, @lines ) = @_;
    my @result;
    for ( my $i = 0; $i < scalar(@lines); $i += 4 ) {
        my @work = ( ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' );
        for ( my $pos = 0; $pos < 9; ++$pos ) {
            $work[$pos] = '?';
            my $got1 = 0;
            for ( my $numeral = 0; $numeral <= 9; ++$numeral ) {
                my $ok = 1;
                for ( my $row = 0; $row < 4; ++$row ) {
                    for ( my $col = 0; $col < 4; ++$col ) {
                        if (
                            substr( $NUMERALS[$numeral][$row], $col,            1 ) ne
                            substr( $lines[ $i + $row ],       4 * $pos + $col, 1 ) ) {
                            $ok = 0;
                        }
                    }
                }
                if ($ok) {
                    $work[$pos] = chr( $numeral + ord('0') );
                    $got1 = 1;
                    last;
                }
            }
            if ( !$got1 ) {
                $work[10] = 'I';
                $work[11] = $work[12] = 'L';
            }
        }
        push @result, join( '', @work );
    }
    return @result;
}

__PACKAGE__->meta->make_immutable;
1;
