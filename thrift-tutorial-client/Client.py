"""
author: RuizhenMai
date: 2022/03/23
"""

from Stub import Stub

from thrift_tutorial_common import UserService, ProductService
from thrift_tutorial_common.ttypes import User, Product

def main():
    # type should be Stub.ProxyWrapper[UserService.Client]
    # but ignore ProxyWrapper to have syntax highlight
    userService: UserService.Client = Stub.getStub(UserService.Client)
    user: User = userService.findUserById(123)
    print(user)

    productService: ProductService.Client = Stub.getStub(ProductService.Client)
    product: Product = productService.findProductById(123)
    print(product)


if __name__ == "__main__":
    main()