

function loadTopics() {


    fetch("http://localhost:8080/topics")
    .then(response => response.json())
    .then(data => {
    console.log(data);
    

        const topicsBody =
            document.getElementById("topicsBody");

        topicsBody.innerHTML = "";

        data.forEach(topic => {

            let statusClass = "";

    if(topic.status === "Completed")
    {
        statusClass = "completed";
    }
    else if(topic.status === "On-Going")
    {
        statusClass = "on-going";
    }
    else
    {
        statusClass = "notstarted";
    }

            const row =
                document.createElement("tr");

            row.innerHTML = `
                <td>${topic.id}</td>
                <td>${topic.name}</td>
                <td>${topic.category}</td>
                <td>${topic.hoursSpent}</td>
                
                <td>
    <select onchange="updateStatus(${topic.id}, this.value)">

        <option value="Not started"
            ${topic.status === "Not started" ? "selected" : ""}>
            Not started
        </option>

        <option value="On-Going"
            ${topic.status === "On-Going" ? "selected" : ""}>
            On-Going
        </option>

        <option value="Completed"
            ${topic.status === "Completed" ? "selected" : ""}>
            Completed
        </option>

    </select>
</td>


                <td>
                <button onclick="deleteTopic(${topic.id})">
                Delete
                </button>
                </td>
            `;

            topicsBody.appendChild(row);
        });
    });
    
}

const form = document.getElementById("topicForm");

form.addEventListener("submit", function(event){

    event.preventDefault();

    const topicName =
        document.getElementById("topicName").value;

    const category =
        document.getElementById("category").value;

    const status =
        document.getElementById("status").value;

    const hoursSpent =
        document.getElementById("hoursSpent").value;


    console.log({
        name: topicName,
        category: category,
        hoursSpent:hoursSpent,
        status: status
    });

    console.log(hoursSpent);

    fetch("http://localhost:8080/topics", {
        method: "POST",
        headers: {
            "Content-Type":"application/json"
        },
        body: JSON.stringify({
            name: topicName,
            category: category,
            hoursSpent:hoursSpent,
            status: status
        })
    })
    .then(response => response.text())
    .then(data => {
        console.log(data);
        loadTopics();
        form.reset();

    });
});

loadTopics();

function deleteTopic(id)
{
    fetch(`http://localhost:8080/topics/${id}`,{
    method:"DELETE"
    })
    .then(response => response.text())
    .then(data=>{
        console.log(data);

        loadTopics();


    });

}

function updateStatus(id,newStatus)
{
    fetch(`http://localhost:8080/topics/${id}`)
    .then(response => response.json())
    .then(topic => {

        topic.status = newStatus;

        return fetch(`http://localhost:8080/topics/${id}`,{
            method:"PUT",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(topic)
        });
    })
    .then(response => response.json())
    .then(data => {

        console.log("Updated");

        loadTopics();
    });
}

function searchCategory()
{
    const category=document.getElementById("searchCategory").value;

    fetch(`http://localhost:8080/topics/category/${category}`)
    .then(response=>response.json())
    .then(data=>{
        
        const topicsBody=document.getElementById("topicsBody");

        topicsBody.innerHTML="";

        data.forEach(topic => 
        {
            const row=document.createElement("tr");

            row.innerHTML=`
            <td>${topic.id}</td>
            <td>${topic.name}</td>
            <td>${topic.category}</td>
            <td>${topic.status}</td>
            <td>
            <button onclick="deleteTopic(${topic.id})">
            Delete
            </button>
            </td>
            `;

            topicsBody.appendChild(row);
        }
        )
    });

}

