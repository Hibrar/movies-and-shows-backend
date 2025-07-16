
<h2>📚 API Endpoints</h2>

<h3>🎬 Movies</h3>
<table>
  <thead>
    <tr>
      <th>Endpoint</th>
      <th>Method</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr><td>/movies</td><td>GET</td><td>List movies with filters</td></tr>
    <tr><td>/movies/:id</td><td>GET</td><td>Get detailed info for a movie</td></tr>
    <tr><td>/movies/:id/credits</td><td>GET</td><td>Get cast and crew for a movie</td></tr>
    <tr><td>/movies/top-rated</td><td>GET</td><td>Get top-rated movies across platforms</td></tr>
    <tr><td>/movies/search</td><td>GET</td><td>Search movies by title, genre, actor, etc.</td></tr>
  </tbody>
</table>

<h3>🧑‍🎤 Actors</h3>
<table>
  <thead>
    <tr>
      <th>Endpoint</th>
      <th>Method</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr><td>/actors</td><td>GET</td><td>List actors with optional filters</td></tr>
    <tr><td>/actors/:id</td><td>GET</td><td>Get actor details</td></tr>
    <tr><td>/actors/:id/movies</td><td>GET</td><td>Get movies featuring the actor</td></tr>
  </tbody>
</table>

<h3>🏢 Platforms</h3>
<table>
  <thead>
    <tr>
      <th>Endpoint</th>
      <th>Method</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr><td>/platforms</td><td>GET</td><td>List all sites</td></tr>
    <tr><td>/platforms/:name</td><td>GET</td><td>Get site overview</td></tr>
    <tr><td>/platforms/:name/movies</td><td>GET</td><td>Get movies available on a site</td></tr>
    <tr><td>/platforms/:name/analytics</td><td>GET</td><td>Get site-specific stats</td></tr>
  </tbody>
</table>

<h3>🎭 Genres</h3>
<table>
  <thead>
    <tr>
      <th>Endpoint</th>
      <th>Method</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr><td>/genres</td><td>GET</td><td>List all genres</td></tr>
    <tr><td>/genres/:name</td><td>GET</td><td>Get genre overview</td></tr>
    <tr><td>/genres/:name/movies</td><td>GET</td><td>Get movies in a genre</td></tr>
    <tr><td>/genres/:name/analytics</td><td>GET</td><td>Genre trends over time</td></tr>
  </tbody>
</table>

<h3>📊 Analytics</h3>
<table>
  <thead>
    <tr>
      <th>Endpoint</th>
      <th>Method</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr><td>/analytics/score-by-year</td><td>GET</td><td>Average score per year</td></tr>
    <tr><td>/analytics/genre-trends</td><td>GET</td><td>Genre popularity over time</td></tr>
    <tr><td>/analytics/platform-comparison</td><td>GET</td><td>Compare platforms by score, volume, etc.</td></tr>
    <tr><td>/analytics/actor-frequency</td><td>GET</td><td>Most frequent actors across platforms</td></tr>
  </tbody>
</table>

<h3>🔍 Search</h3>
<table>
  <thead>
    <tr>
      <th>Endpoint</th>
      <th>Method</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr><td>/search</td><td>GET</td><td>Unified search across movies, actors, platforms</td></tr>
  </tbody>
</table>