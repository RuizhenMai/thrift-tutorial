"""
author: RuizhenMai
date: 2022/03/23

This file is in charge of 
1. creating connection to server 
2. invoking calls to the server 
"""

import sys
sys.path.append("gen-py")
sys.path.append("gen-py/shared")
sys.path.append("gen-py/thrift_tutorial_common")

from thrift.transport import TSocket, TTransport
from thrift.protocol import TBinaryProtocol, TMultiplexedProtocol

from shared.SharedService import Client

from typing import Type

class Stub:
    
    class ProxyWrapper:
        def __init__(self, wrappee: Type[Client]):
            self.wrappee = wrappee
        
        def __getattr__(self, attr):
            def invoke(*args, **kwargs):
                # Create a Socket via Thrift's Socket constructor
                transport = TSocket.TSocket("localhost", 9090)

                # Wrap the socket with a buffer
                transport = TTransport.TBufferedTransport(transport)

                # Wrap the socket with binary seralization
                protocol = TBinaryProtocol.TBinaryProtocol(transport)
                
                # Wrap the protocol with Multiplex service
                className = str(self.wrappee).split(".")[-2]
                protocol = TMultiplexedProtocol.TMultiplexedProtocol(protocol, className)

                client = self.wrappee(protocol)
                
                # Open the socket connection
                transport.open()

                # Invoke remote function
                res = getattr(client, attr)(*args, **kwargs)

                # Close the socket 
                transport.close()

                return res
            return invoke


    @staticmethod
    def getStub(serviceClazz: Type[Client]) -> ProxyWrapper:
        """Return a Proxy Wrapper of a predefined Service 

        Args:
            serviceClazz (Type[Client]): a defined (e.g. in .thrift file) service class 

        Returns:
            ProxyWrapper: return an instance of ProxyWrapper[Client]
        """
        return Stub.ProxyWrapper(serviceClazz)

        

