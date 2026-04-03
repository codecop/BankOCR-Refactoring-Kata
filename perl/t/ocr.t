use strict;
use warnings;
use Test::More;
use FindBin qw($Bin);
use lib "$Bin/../lib";
use Ocr;

my $ocr = Ocr->new();

subtest 'parses_one_record_with_888888888' => sub {
    my @result = $ocr->parse(
        ' _   _   _   _   _   _   _   _   _  ',
        '|_| |_| |_| |_| |_| |_| |_| |_| |_| ',
        '|_| |_| |_| |_| |_| |_| |_| |_| |_| ',
        '                                    ',
    );
    is_deeply(\@result, ['888888888    ']);
};

subtest 'parses_two_records_with_888888888' => sub {
    my @result = $ocr->parse(
        ' _   _   _   _   _   _   _   _   _  ',
        '|_| |_| |_| |_| |_| |_| |_| |_| |_| ',
        '|_| |_| |_| |_| |_| |_| |_| |_| |_| ',
        '                                    ',
        ' _   _   _   _   _   _   _   _   _  ',
        '|_| |_| |_| |_| |_| |_| |_| |_| |_| ',
        '|_| |_| |_| |_| |_| |_| |_| |_| |_| ',
        '                                    ',
    );
    is_deeply(\@result, ['888888888    ', '888888888    ']);
};

subtest 'parses_one_record_with_123456790' => sub {
    my @result = $ocr->parse(
        '     _   _       _   _   _   _   _  ',
        '  |  _|  _| |_| |_  |_    | |_| | | ',
        '  | |_   _|   |  _| |_|   |  _| |_| ',
        '                                    ',
    );
    is_deeply(\@result, ['123456790    ']);
};

subtest 'parses_two_records' => sub {
    my @result = $ocr->parse(
        '     _   _       _   _   _   _   _  ',
        '  |  _|  _| |_| |_  |_    | |_| | | ',
        '  | |_   _|   |  _| |_|   |  _| |_| ',
        '                                    ',
        ' _       _   _   _       _   _   _  ',
        '|_| |_|   | |_|   | |_|  _|  _|  _| ',
        ' _|   |   | |_|   |   | |_  |_  |_  ',
        '                                    ',
    );
    is_deeply(\@result, ['123456790    ', '947874222    ']);
};

subtest 'parses_illegal_digit' => sub {
    my @result = $ocr->parse(
        '     _   _       _   _   _   _   _  ',
        '  |  _| |_|  _| |_  |_    | |_| | | ',
        '  | |_   _    |  _| |_|   |  _| |_| ',
        '                                    ',
    );
    is_deeply(\@result, ['12??56790 ILL']);
};

# subtest 'checksum_fail_returns_ERR' => sub {
#     my @result = $ocr->parse(
#         ' _   _       _   _           _   _  ',
#         '|_  |_  |_|  _|   |   | |_| |_| |_  ',
#         '|_| |_|   |  _|   |   |   |  _|  _| ',
#         '                                    ',
#     );
#     is_deeply(\@result, ['664371495 ERR']);
# };

done_testing;
