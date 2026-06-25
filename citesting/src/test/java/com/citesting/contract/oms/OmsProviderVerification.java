package com.citesting.contract.oms;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.VersionSelector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
 
 

@SuppressWarnings("deprecation")
@Provider("oms-provider")
@PactBroker(
    url = "http://localhost:9292",
    consumerVersionSelectors = {
        @VersionSelector(tag = "week4") // matches your maven publish tag
    }
)
public class OmsProviderVerification {
 
 
    @SuppressWarnings("JUnitMalformedDeclaration")
    @BeforeEach
    void setup(PactVerificationContext context){
        context.setTarget(new HttpTestTarget("localhost", 4010, "/"));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void verify(PactVerificationContext context){
        context.verifyInteraction();
    }

    @State("Order 123 exists")
    void isOrderExists(){
    }
}
