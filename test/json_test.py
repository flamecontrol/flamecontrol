import json

text = '{"user":{"id":2377,"realname":null,"nickname":null,"email":null,"mobile":"11469686357","is_confirmed":true,"role":null,"bio":null,"avatar":null,"is_approvaled":false,"is_disabled":false,"wechat":null,"created_at":"2016-07-28T14:12:38.056+08:00","updated_at":"2016-07-28T14:12:38.056+08:00"},"access_token":{"expires_in":null,"scopes":["public"],"revoked_at":null,"refresh_token":"ab258dc242301fbf1fff8ba52f45d7e8bd728fb40fa01dc141909cbffafa7be9","access_token":"a3b66d952a188d2bb86b30ca3aa6847d6ad8ab099aeaa590fe08dc9bbf065a93"}}'
token_key = 'access_token'
token_value = json.loads(text)
while type(token_value) != str:
    token_value = token_value[token_key]

print(token_value)
print(type(token_value))

