<script>
	import { onMount } from "svelte";

	let gameIsOn = false;
	let guessNumber = 1;
	let sessionId = "";
	let guessSuccess = "";

	onMount(async function() {
		getSession();
	});

	async function getSession() {
		const response = await fetch("http://localhost:8080/api/guess/start");
		const text = await response.text();
		sessionId = text;
		sessionStorage.setItem('sessionId', sessionId);
	}

	async function makeGuess() {
		let url = "http://localhost:8080/api/guess/"+ sessionId + "/" + guessNumber;
		let options = {
            method: 'POST',
            mode: 'cors',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            }
        };
		let response = await fetch(url, options);
		let responseOK = response && response.ok;
		if (responseOK) {
		    let data = await response.text();
				guessSuccess = data;
				if(guessSuccess == '"CORRECT"') {
					gameIsOn = false;
				}
		}
	}

	function startGame() {
		getSession();
		gameIsOn = true;
	}
</script>

{#if gameIsOn}
	<label>
		<input type=number bind:value={guessNumber} min=1 max=100>
		<input type=range bind:value={guessNumber} min=1 max=100>
	</label>

	<button on:click={makeGuess}>
		Tippelek
		</button>
{/if}

{#if !gameIsOn && guessSuccess === '"CORRECT"'}
	<h2> GOOD JOB </h2>
	<h3> Your number was {guessNumber} </h3>
{/if}

{#if !gameIsOn}
	<h2>All you have to do is guess the number between 1 and 100</h2>
	<h4>Ready?</h4>
	<button on:click={startGame}>
		Start game
	</button>
{/if}

{#if guessSuccess === '"HIGHER"'}
	<h3>Nice try,  try a little higher</h3>
{/if}

{#if guessSuccess === '"LOWER"'}
	<h3>Too high!</h3>
{/if}
