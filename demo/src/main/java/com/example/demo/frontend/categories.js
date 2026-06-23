
function loadCategory(category)
{
    fetch(`http://localhost:8080/topics/category/${category}`)
    .then(response=> response.json())
    .then(data =>{
        console.log(data);

        const topicList=document.getElementById("topicList");

        topicList.innerHTML="";

        data.forEach(topic=>{
            
            const li=document.createElement("li");

            li.textContent=topic.name;


            topicList.appendChild(li);



        })
        
    })
}