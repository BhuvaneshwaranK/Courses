function [p,v] = snake(m)
    mapSize = size(m.links,2);
    rproba = riskyDiceProb(m);
    sproba = securityDiceProb(m);

    v = ones(1,mapSize);
    p = ones(1,mapSize);
    c = ones(1,mapSize);
    vold=v;
    v(m.d) = 0;
    while(abs(vold*vold' - v*v') > 1e-5)
        vold = v;
        for i = 1:mapSize
            if i ~= m.d
                v(i) = min([(c(i) + sproba(i,:) * v'), (c(i) + rproba(i,:) * v')]);
             end
        end
        for i = 1:mapSize
            [ans p(i)] = min([(sproba(i,:) * (v + ones(1,mapSize)*c(i))') (c(i) + rproba(i,:) * v')]);
        end 
    end
       
end
function probas = securityDiceProb(m)
    probas = zeros(size(m.links));
    for i = 1:size(m.links,1)
        probas(i,i) = 0.5; 
        np = next_paths(i,m);
        for j = np
            probas(i,j)= probas(i,j) + 0.5/size(np,2);
        end
    end
end

function probas = riskyDiceProb(m)
    probas = zeros(size(m.links));
    for i = 1:size(m.links,1)
        %if the dice is 0
        probas(i,:) = probas(i,:) + trap(i,m,(1/3));

        %if the dice is not 0
        np = next_paths(i,m);
        for j = np
            %if the dice is 1
            probas(i,:) = probas(i,:) + trap(j,m,(1/3)/size(np,2));
            

            %if the dice is 2
            %we go in the primary state after j
            k = next_primary(j,m);
            probas(i,:) = probas(i,:) + trap(k,m,(1/3)/size(np,2));

        end
    end
end

function pline = trap(s,m,prob)
    pline = zeros(1,size(m.links,2));
    if m.traps(s) == 1
        pline(m.s0) = pline(m.s0)+prob;
    else
        pline(s) = pline(s) + prob;
    end
end