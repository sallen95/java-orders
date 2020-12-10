package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Agent;
import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.models.Order;

import java.util.List;

public interface AgentServices
{
    Agent save(Agent agent);
    Agent findByAgentId(long id);
}
