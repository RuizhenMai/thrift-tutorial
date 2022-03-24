namespace java com.example.gen.thrift

include "shared.thrift"

struct User {
    1: i32 uid,
    2: string uname
}

service UserService extends shared.SharedService {
    User findUserById(1: i32 uid),
}

struct Product {
    1: i32 pid,
    2: string pname
}

service ProductService extends shared.SharedService {
    Product findProductById(1: i32 pid),
}