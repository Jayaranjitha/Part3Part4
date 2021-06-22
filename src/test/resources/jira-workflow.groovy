when 'Open', {
	'success' should: 'Test passed'
}

when 'Reopened', {
	'success' should: 'Test passed'
}

when 'Resolved', {
	'failure' should: 'Reopen Issue'
}

when 'In Progress', {
	'success' should: 'Resolve Issue'
}

when 'Closed', {
	'failure' should: 'Reopen Issue'
}